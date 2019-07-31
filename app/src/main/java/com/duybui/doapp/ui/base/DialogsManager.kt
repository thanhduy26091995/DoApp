package com.duybui.doapp.ui.base

import android.os.Bundle
import android.text.TextUtils
import androidx.annotation.UiThread
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

/**
 * This object should be used in activities and fragments in order to manage dialogs.
 */
@UiThread
class DialogsManager(private val mFragmentManager: FragmentManager) {
    /**
     * @return a reference to currently shown dialog, or null if no dialog is shown.
     */
    var currentlyShownDialog: DialogFragment? = null
        private set

    /**
     * Obtain the id of the currently shown dialog.
     * @return the id of the currently shown dialog; empty string if no dialog is shown, or the currently
     * shown dialog has no id
     */
    val currentlyShownDialogId: String?
        get() = if (currentlyShownDialog == null || currentlyShownDialog!!.arguments == null ||
            !currentlyShownDialog!!.arguments!!.containsKey(ARGUMENT_DIALOG_ID)
        ) {
            ""
        } else {
            currentlyShownDialog!!.arguments!!.getString(ARGUMENT_DIALOG_ID)
        }

    init {

        // there might be some dialog already shown
        val fragmentWithDialogTag = mFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG)
        if (fragmentWithDialogTag != null && DialogFragment::class.java.isAssignableFrom(fragmentWithDialogTag.javaClass)) {
            currentlyShownDialog = fragmentWithDialogTag as DialogFragment?
        }
    }

    /**
     * Check whether a dialog with a specified id is currently shown
     * @param id dialog id to query
     * @return true if a dialog with the given id is currently shown; false otherwise
     */
    fun isDialogCurrentlyShown(id: String): Boolean {
        val shownDialogId = currentlyShownDialogId
        return !TextUtils.isEmpty(shownDialogId) && shownDialogId == id
    }

    /**
     * Dismiss the currently shown dialog. Has no effect if no dialog is shown. Please note that
     * we always allow state loss upon dismissal.
     */
    fun dismissCurrentlyShownDialog() {
        if (currentlyShownDialog != null) {
            currentlyShownDialog!!.dismissAllowingStateLoss()
            currentlyShownDialog = null
        }
    }

    /**
     * Show dialog and assign it a given "id". Replaces any other currently shown dialog.
     * @param dialog dialog to show
     * @param id string that uniquely identifies the dialog; can be null
     */
    fun showDialogWithId(dialog: DialogFragment, id: String?, title: String, body: String) {
        dismissCurrentlyShownDialog()
        setId(dialog, id)
        showDialog(dialog)
    }

    private fun setContent(title: String, body: String) {

    }

    private fun setId(dialog: DialogFragment, id: String?) {
        val args = if (dialog.arguments != null) dialog.arguments else Bundle(1)
        args!!.putString(ARGUMENT_DIALOG_ID, id)
        dialog.arguments = args
    }

    fun showDialog(dialog: DialogFragment) {
        dismissCurrentlyShownDialog()
        mFragmentManager.beginTransaction()
            .add(dialog, DIALOG_FRAGMENT_TAG)
            .commitAllowingStateLoss()
        currentlyShownDialog = dialog
    }

    companion object {

        /**
         * Whenever a dialog is shown with non-empty "id", the provided id will be stored in
         * arguments Bundle under this key.
         */
        val ARGUMENT_DIALOG_ID = "ARGUMENT_DIALOG_ID"

        /**
         * In case Activity or Fragment that instantiated this DialogsManager are re-created (e.g.
         * in case of memory reclaim by OS, orientation change, etc.), we need to be able
         * to get a reference to dialog that might have been shown. This tag will be supplied with
         * all DialogFragment's shown by this DialogsManager and can be used to query
         * [FragmentManager] for last shown dialog.
         */
        private val DIALOG_FRAGMENT_TAG = "DIALOG_FRAGMENT_TAG"
    }

}
