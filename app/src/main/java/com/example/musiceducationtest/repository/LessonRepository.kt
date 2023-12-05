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
            flowChartBlocks = listOf(
                BlockDataModel(
                    id = "block01t",
                    type = BlockType.MUSIC,
                    imageResId = R.drawable.ttls_01,
                    musicResId = R.raw.ttls_01
                ),
                BlockDataModel(
                    id = "block02t",
                    type = BlockType.MUSIC,
                    imageResId = R.drawable.ttls_02,
                    musicResId = R.raw.ttls_02
                ),
                BlockDataModel(
                    id = "block03t",
                    type = BlockType.MUSIC,
                    imageResId = R.drawable.ttls_03,
                    musicResId = R.raw.ttls_03
                ),
                BlockDataModel(
                    id = "block04t",
                    type = BlockType.MUSIC,
                    imageResId = R.drawable.ttls_04,
                    musicResId = R.raw.ttls_04
                ),
                BlockDataModel(
                    id = "block05t",
                    type = BlockType.MUSIC,
                    imageResId = R.drawable.ttls_05,
                    musicResId = R.raw.ttls_05
                ),
                BlockDataModel(
                    id = "block06t",
                    type = BlockType.MUSIC,
                    imageResId = R.drawable.ttls_06,
                    musicResId = R.raw.ttls_06
                ),
                BlockDataModel(
                    id = "block07t",
                    type = BlockType.MUSIC,
                    imageResId = R.drawable.ttls_07,
                    musicResId = R.raw.ttls_07
                ),
                BlockDataModel(
                    id = "block08t",
                    type = BlockType.MUSIC,
                    imageResId = R.drawable.ttls_08,
                    musicResId = R.raw.ttls_08
                ),
                BlockDataModel(
                    id = "block09t",
                    type = BlockType.MUSIC,
                    imageResId = R.drawable.ttls_09,
                    musicResId = R.raw.ttls_09
                ),
                BlockDataModel(
                    id = "block10t",
                    type = BlockType.MUSIC,
                    imageResId = R.drawable.ttls_10,
                    musicResId = R.raw.ttls_10
                ),
                BlockDataModel(
                    id = "block11t",
                    type = BlockType.MUSIC,
                    imageResId = R.drawable.ttls_11,
                    musicResId = R.raw.ttls_11
                ),
                BlockDataModel(
                    id = "block12t",
                    type = BlockType.MUSIC,
                    imageResId = R.drawable.ttls_12,
                    musicResId = R.raw.ttls_12
                ),
            ),
            answers = listOf("Answer1", "Answer2")
        ),
        LessonDataModel(
            id = "2",
            title = "レッスン2",
            songTitle = "うみはひろいな",
            description = "このレッスンでは「うみはひろいな」を題材にした学習を行います。",
            musicResId = R.raw.sea,
            flowChartBlocks = listOf(
                BlockDataModel(
                    id = "block01s",
                    type = BlockType.MUSIC,
                    imageResId = R.drawable.sea_01,
                    musicResId = R.raw.ttls_01
                ),
                BlockDataModel(
                    id = "block02s",
                    type = BlockType.MUSIC,
                    imageResId = R.drawable.sea_02,
                    musicResId = R.raw.ttls_02
                ),
                BlockDataModel(
                    id = "block03s",
                    type = BlockType.MUSIC,
                    imageResId = R.drawable.sea_03,
                    musicResId = R.raw.ttls_03
                ),
                BlockDataModel(
                    id = "block04s",
                    type = BlockType.MUSIC,
                    imageResId = R.drawable.sea_04,
                    musicResId = R.raw.ttls_04
                ),
                BlockDataModel(
                    id = "block05s",
                    type = BlockType.MUSIC,
                    imageResId = R.drawable.sea_05,
                    musicResId = R.raw.ttls_05
                ),
                BlockDataModel(
                    id = "block06s",
                    type = BlockType.MUSIC,
                    imageResId = R.drawable.sea_06,
                    musicResId = R.raw.ttls_06
                ),
                BlockDataModel(
                    id = "block07s",
                    type = BlockType.MUSIC,
                    imageResId = R.drawable.sea_07,
                    musicResId = R.raw.ttls_07
                ),
                BlockDataModel(
                    id = "block08s",
                    type = BlockType.MUSIC,
                    imageResId = R.drawable.sea_08,
                    musicResId = R.raw.ttls_08
                ),
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