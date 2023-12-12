package com.example.musiceducationtest.repository

import com.example.musiceducationtest.R
import com.example.musiceducationtest.model.BlockDataModel
import com.example.musiceducationtest.model.BlockType
import com.example.musiceducationtest.model.LessonDataModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LessonRepository @Inject constructor() {
    private val lessons = listOf(
        LessonDataModel(
            id = "1",
            title = "レッスン1",
            songTitle = "きらきら星",
            description = "このレッスンでは「きらきら星」を題材にした学習を行います。",
            musicResId = R.raw.twinkle_twinkle_little_star,
            firstFlowChartBlock = BlockDataModel.BLOCK01T,
            flowChartBlocks = listOf(
                BlockDataModel.BLOCK02T,
                BlockDataModel.BLOCK03T,
                BlockDataModel.BLOCK04T,
                BlockDataModel.BLOCK05T,
                BlockDataModel.BLOCK06T,
                BlockDataModel.BLOCK07T,
                BlockDataModel.BLOCK08T,
                BlockDataModel.BLOCK09T,
                BlockDataModel.BLOCK10T,
                BlockDataModel.BLOCK11T,
                BlockDataModel.BLOCK12T
            ),
            answers = listOf("Answer1", "Answer2")
        ),
        LessonDataModel(
            id = "2",
            title = "レッスン2",
            songTitle = "うみはひろいな",
            description = "このレッスンでは「うみはひろいな」を題材にした学習を行います。",
            musicResId = R.raw.sea,
            firstFlowChartBlock = BlockDataModel.BLOCK01S,
            flowChartBlocks = listOf(
                BlockDataModel.BLOCK02S,
                BlockDataModel.BLOCK03S,
                BlockDataModel.BLOCK04S,
                BlockDataModel.BLOCK05S,
                BlockDataModel.BLOCK06S,
                BlockDataModel.BLOCK07S,
                BlockDataModel.BLOCK08S,
            ),
            answers = listOf("Answer3", "Answer4")
        )

        // 他のレッスンを追加...

    )

    // 選択されたレッスンを取得するメソッド
    fun getLessonById(id: String): LessonDataModel? {
        return lessons.find { it.id == id }?.apply {
            // flowChartBlocks の順序をランダムにする
            flowChartBlocks = flowChartBlocks.shuffled()
        }
    }

    // 全種類のレッスンを取得するためのメソッド
    fun getAllLessons(): List<LessonDataModel> {
        return lessons
    }
}