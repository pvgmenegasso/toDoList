package com.example.pedro.todolist.CenarioToDoActivity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity
data class TODO(var text: String = "",
                @PrimaryKey(autoGenerate = true)
                var id : Int = 0 ): Serializable
