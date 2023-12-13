package com.example.musiceducationtest.repository

import com.example.musiceducationtest.R
import com.example.musiceducationtest.model.BlockDataModel
import com.example.musiceducationtest.model.LessonDataModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LessonRepository @Inject constructor() {
    private val lessons = listOf(
        LessonDataModel(
            id = "0",
            title = "レッスン0",
            songTitle = "きらきら星（開発用）",
            description = "このレッスンでは「きらきら星」を題材にした学習を行います。",
            musicResId = R.raw.twinkle_twinkle_little_star,
            firstFlowChartBlock = BlockDataModel.BLOCK01T_T,
            flowChartBlocks = listOf(
                BlockDataModel.BLOCK02T_T,
                BlockDataModel.BLOCK03T_T,
                BlockDataModel.BLOCK04T_T,
                BlockDataModel.BLOCK05T_T,
                BlockDataModel.BLOCK06T_T,
                BlockDataModel.BLOCK07T_T,
                BlockDataModel.BLOCK08T_T,
                BlockDataModel.BLOCK09T_T,
                BlockDataModel.BLOCK10T_T,
                BlockDataModel.BLOCK11T_T,
                BlockDataModel.BLOCK12T_T
            ),
            answers = listOf(
                BlockDataModel.BLOCK01T_T,
                BlockDataModel.BLOCK02T_T,
                BlockDataModel.BLOCK03T_T,
                BlockDataModel.BLOCK04T_T,
                BlockDataModel.BLOCK05T_T,
                BlockDataModel.BLOCK06T_T,
                BlockDataModel.BLOCK07T_T,
                BlockDataModel.BLOCK08T_T,
                BlockDataModel.BLOCK09T_T,
                BlockDataModel.BLOCK10T_T,
                BlockDataModel.BLOCK11T_T,
                BlockDataModel.BLOCK12T_T
            )
        ),
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
            answers = listOf(
                BlockDataModel.BLOCK01T,
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
            )
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
            answers = listOf(
                BlockDataModel.BLOCK01S,
                BlockDataModel.BLOCK02S,
                BlockDataModel.BLOCK03S,
                BlockDataModel.BLOCK04S,
                BlockDataModel.BLOCK05S,
                BlockDataModel.BLOCK06S,
                BlockDataModel.BLOCK07S,
                BlockDataModel.BLOCK08S,
            )
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