package com.motasem.ziad.assignmentlab6.model

import com.google.gson.annotations.SerializedName

data class ToDoData(var userId: String, var id: String, var title: String, var completed: Boolean)