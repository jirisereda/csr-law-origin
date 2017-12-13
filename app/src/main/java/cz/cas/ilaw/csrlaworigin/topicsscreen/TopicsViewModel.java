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

package cz.cas.ilaw.csrlaworigin.topicsscreen;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import cz.cas.ilaw.csrlaworigin.db.DatabaseHelper;
import cz.cas.ilaw.csrlaworigin.db.User;
import java.util.List;
import javax.inject.Inject;

public class TopicsViewModel extends ViewModel {

    public LiveData<List<User>> users;

    private DatabaseHelper mDatabaseHelper;
    private UserRepository mUserRepository;

    @Inject
    public TopicsViewModel(DatabaseHelper databaseHelper, UserRepository userRepository) {
        mDatabaseHelper = databaseHelper;
        mUserRepository = userRepository;
        createDb();
    }

    public void createDb() {
        // Receive changes
        subscribeToDbChanges();
    }

    private void subscribeToDbChanges() {
        // Books is a LiveData object so updates are observed.
        users = mDatabaseHelper.loadAllUsers();
    }

    public void load() {
        Log.d("TopicsViewModel", "load");
        mUserRepository.getTickets();
    }

    public LiveData<List<User>> getUser() {
        return users;
    }
}
