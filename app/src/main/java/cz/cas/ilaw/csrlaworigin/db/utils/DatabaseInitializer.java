/*
 * Copyright 2017, The Android Open Source Project
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
 * limitations under the License.
 */

package cz.cas.ilaw.csrlaworigin.db.utils;

import android.os.AsyncTask;

import cz.cas.ilaw.csrlaworigin.db.AppDatabase;
import cz.cas.ilaw.csrlaworigin.db.RecordTopic;
import cz.cas.ilaw.csrlaworigin.db.User;
import cz.cas.ilaw.csrlaworigin.topicsscreen.Ticket;
import cz.cas.ilaw.csrlaworigin.topicsscreen.UserResponse;
import java.util.List;
import retrofit2.Response;

public class DatabaseInitializer {

    public static void populateAsync(final AppDatabase db) {

        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public static void addRecordTopicAsync(final AppDatabase db, final RecordTopic recordTopic) {

        AddRecordToDbAsync task = new AddRecordToDbAsync(db, recordTopic);
        task.execute();
    }

    //private static void addRecordTopic(final AppDatabase db, final String title, final String description) {
    //    RecordTopic recordTopic = new RecordTopic();
    //    recordTopic.setTitle(title);
    //    recordTopic.setDescription(description);
    //    db.recordTopicModel().insertRecordTopic(recordTopic);
    //}
    //
    //private static void addRecordTopic(final AppDatabase db, final RecordTopic recordTopic) {
    //    db.recordTopicModel().insertRecordTopic(recordTopic);
    //}
    //
    //private static void populateWithTestData(AppDatabase db) {
    //    db.recordTopicModel().deleteAll();
    //
    //    addRecordTopic(db, "Heslo 1", "Popis heslo 1");
    //    addRecordTopic(db, "Heslo 2", "Popis heslo 2");
    //    addRecordTopic(db, "Heslo 3", "Popis heslo 3");
    //}
    //
    //public static User addUser(final AppDatabase db, final String name) {
    //    User user = new User();
    //    user.name = name;
    //    db.userModel().save(user);
    //    //db.userModel().hashCode();
    //    return user;
    //}
    //
    //
    //public static void addTicket(final AppDatabase db, UserResponse userResponse) {
    //    User user = new User();
    //    user.name = userResponse.getName();
    //    db.userModel().save(user);
    //}

    //public static void addTickets(final AppDatabase db, List<UserResponse> listResponse) {
    //
    //    for (UserResponse response: listResponse) {
    //        addTicket(db, response);
    //    }
    //}


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            //populateWithTestData(mDb);
            return null;
        }
    }

    private static class AddRecordToDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;
        final RecordTopic mRecordTopic;

        AddRecordToDbAsync(AppDatabase db, RecordTopic recordTopic) {
            mDb = db;
            mRecordTopic = recordTopic;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            //addRecordTopic(mDb, mRecordTopic);
            return null;
        }

    }
}
