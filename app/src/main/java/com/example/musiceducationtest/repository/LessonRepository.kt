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
                BlockDataModel(id = "block01", type = BlockType.MUSIC ,imageResId = R.drawable.ttls_01, musicResId = R.raw.ttls_01),
                BlockDataModel(id = "block02", type = BlockType.MUSIC ,imageResId = R.drawable.ttls_02, musicResId = R.raw.ttls_02),
                BlockDataModel(id = "block03", type = BlockType.MUSIC ,imageResId = R.drawable.ttls_03, musicResId = R.raw.ttls_03),
                BlockDataModel(id = "block04", type = BlockType.MUSIC ,imageResId = R.drawable.ttls_04, musicResId = R.raw.ttls_04),
                BlockDataModel(id = "block05", type = BlockType.MUSIC ,imageResId = R.drawable.ttls_05, musicResId = R.raw.ttls_05),
                BlockDataModel(id = "block06", type = BlockType.MUSIC ,imageResId = R.drawable.ttls_06, musicResId = R.raw.ttls_06),
                BlockDataModel(id = "block07", type = BlockType.MUSIC ,imageResId = R.drawable.ttls_07, musicResId = R.raw.ttls_07),
                BlockDataModel(id = "block08", type = BlockType.MUSIC ,imageResId = R.drawable.ttls_08, musicResId = R.raw.ttls_08),
                BlockDataModel(id = "block09", type = BlockType.MUSIC ,imageResId = R.drawable.ttls_09, musicResId = R.raw.ttls_09),
                BlockDataModel(id = "block10", type = BlockType.MUSIC ,imageResId = R.drawable.ttls_10, musicResId = R.raw.ttls_10),
                BlockDataModel(id = "block11", type = BlockType.MUSIC ,imageResId = R.drawable.ttls_11, musicResId = R.raw.ttls_11),
                BlockDataModel(id = "block12", type = BlockType.MUSIC ,imageResId = R.drawable.ttls_12, musicResId = R.raw.ttls_12),
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
                //BlockDataModel(id = "block1", imageResId = R.drawable.some_image, musicResId = R.raw.some_music_file),
                //BlockDataModel(id = "block2", imageResId = R.drawable.some_image, musicResId = R.raw.some_music_file),
            ),
            answers = listOf("Answer3", "Answer4")
        )
        // 他のレッスンを追加...
    )

    // 選択されたレッスンを取得するメソッド
    fun getLessonById(id: String): LessonDataModel? {
        return lessons.find { it.id == id }
    }

    // 全種類のレッスンを取得するためのメソッド
    fun getAllLessons(): List<LessonDataModel> {
        return lessons
    }
}