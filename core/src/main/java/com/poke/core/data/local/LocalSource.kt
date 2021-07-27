package com.poke.core.data.local

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.poke.core.data.database.model.Poke

interface LocalSource {
    val pokeLiveData: LiveData<PagedList<Poke>>
}