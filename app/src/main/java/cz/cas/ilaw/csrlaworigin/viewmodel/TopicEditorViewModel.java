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

package cz.cas.ilaw.csrlaworigin.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import cz.cas.ilaw.csrlaworigin.db.AppDatabase;
import cz.cas.ilaw.csrlaworigin.db.RecordTopic;
import cz.cas.ilaw.csrlaworigin.db.utils.DatabaseInitializer;


public class TopicEditorViewModel extends AndroidViewModel {

    private LiveData<List<RecordTopic>> mRecordTopics;

    private AppDatabase mDb;

    public TopicEditorViewModel(Application application) {
        super(application);
        createDb();
    }

    public void createDb() {
        //mDb = AppDatabase.getInMemoryDatabase(this.getApplication());

        // Populate it with initial data
//        DatabaseInitializer.populateAsync(mDb);

        // Receive changes
        subscribeToDbChanges();
    }

    public LiveData<List<RecordTopic>> getBooks() {
        return mRecordTopics;
    }

    private void subscribeToDbChanges() {
        // Books is a LiveData object so updates are observed.
        //mRecordTopics = mDb.recordTopicModel().loadAllTopics();
    }

    public void saveTopic(String title, String description) {

        RecordTopic recordTopic = new RecordTopic();
        recordTopic.setTitle(title);
        recordTopic.setDescription(description);

        DatabaseInitializer.addRecordTopicAsync(mDb, recordTopic);
    }

}
