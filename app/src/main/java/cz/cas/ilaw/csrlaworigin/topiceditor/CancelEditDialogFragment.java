/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package cz.cas.ilaw.csrlaworigin.topiceditor;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;

import cz.cas.ilaw.csrlaworigin.R;

/**
 * Asks the user whether to cancel editing the item.
 */
public class CancelEditDialogFragment extends DialogFragment {

    private static final String TAG = "cancelEditor";

    public static void show(FragmentTransaction transaction) {
        CancelEditDialogFragment cancelEditDialogFragment = new CancelEditDialogFragment();
        cancelEditDialogFragment.show(transaction, TAG);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setIconAttribute(android.R.attr.alertDialogIcon)
                .setMessage(R.string.cancel_confirmation_dialog_message)
                .setPositiveButton(R.string.cancel_confirmation_dialog_cancel_editing_button,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {

                                ((EntityActivity) getActivity()).doPositiveClick();
                            }
                        }
                )
                .setNegativeButton(R.string.cancel_confirmation_dialog_keep_editing_button, null)
                .create();
    }
}
