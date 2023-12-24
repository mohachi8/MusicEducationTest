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
            id = "0.1",
            title = "レッスン0.1",
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
                BlockDataModel.BLOCK12T_T,
                BlockDataModel.BLOCK01S,
                BlockDataModel.BLOCK02S
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
            id = "0.2",
            title = "レッスン0.2",
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
                BlockDataModel.BLOCK09T_T,
                BlockDataModel.BLOCK10T_T,
                BlockDataModel.BLOCK11T_T,
                BlockDataModel.BLOCK12T_T,
                BlockDataModel.REPEAT_START,
                BlockDataModel.REPEAT_END
            ),
            answers = listOf(
                BlockDataModel.BLOCK01T_T,
                BlockDataModel.BLOCK02T_T,
                BlockDataModel.BLOCK03T_T,
                BlockDataModel.BLOCK04T_T,
                BlockDataModel.REPEAT_START,
                BlockDataModel.BLOCK05T_T,
                BlockDataModel.BLOCK06T_T,
                BlockDataModel.REPEAT_END,
                BlockDataModel.BLOCK09T_T,
                BlockDataModel.BLOCK10T_T,
                BlockDataModel.BLOCK11T_T,
                BlockDataModel.BLOCK12T_T
            )
        ),
        LessonDataModel(
            id = "0.3",
            title = "レッスン0.3",
            songTitle = "きらきら星（MIDI）",
            description = "このレッスンでは「きらきら星」を題材にした学習を行います。",
            musicResId = R.raw.twinkle_twinkle_little_star,
            firstFlowChartBlock = BlockDataModel.BLOCK01T_M,
            flowChartBlocks = listOf(
                BlockDataModel.BLOCK02T_M,
                BlockDataModel.BLOCK03T_M,
                BlockDataModel.BLOCK04T_M,
                BlockDataModel.BLOCK05T_M,
                BlockDataModel.BLOCK06T_M,
                BlockDataModel.BLOCK09T_M,
                BlockDataModel.BLOCK10T_M,
                BlockDataModel.BLOCK11T_M,
                BlockDataModel.BLOCK12T_M,
                BlockDataModel.REPEAT_START,
                BlockDataModel.REPEAT_END
            ),
            answers = listOf(
                BlockDataModel.BLOCK01T_M,
                BlockDataModel.BLOCK02T_M,
                BlockDataModel.BLOCK03T_M,
                BlockDataModel.BLOCK04T_M,
                BlockDataModel.REPEAT_START,
                BlockDataModel.BLOCK05T_M,
                BlockDataModel.BLOCK06T_M,
                BlockDataModel.REPEAT_END,
                BlockDataModel.BLOCK09T_M,
                BlockDataModel.BLOCK10T_M,
                BlockDataModel.BLOCK11T_M,
                BlockDataModel.BLOCK12T_M,
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
                BlockDataModel.BLOCK09T,
                BlockDataModel.BLOCK10T,
                BlockDataModel.BLOCK11T,
                BlockDataModel.BLOCK12T,
                BlockDataModel.REPEAT_START,
                BlockDataModel.REPEAT_END
            ),
            answers = listOf(
                BlockDataModel.BLOCK01T,
                BlockDataModel.BLOCK02T,
                BlockDataModel.BLOCK03T,
                BlockDataModel.BLOCK04T,
                BlockDataModel.REPEAT_START,
                BlockDataModel.BLOCK05T,
                BlockDataModel.BLOCK06T,
                BlockDataModel.REPEAT_END,
                BlockDataModel.BLOCK09T,
                BlockDataModel.BLOCK10T,
                BlockDataModel.BLOCK11T,
                BlockDataModel.BLOCK12T,
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
            val repeatStartExists = BlockDataModel.REPEAT_START in flowChartBlocks
            val repeatEndExists = BlockDataModel.REPEAT_END in flowChartBlocks
            // REPEAT_START と REPEAT_END の位置を取得
            val repeatStartIndex =
                if (repeatStartExists) flowChartBlocks.indexOf(BlockDataModel.REPEAT_START) else -1
            val repeatEndIndex =
                if (repeatEndExists) flowChartBlocks.indexOf(BlockDataModel.REPEAT_END) else -1

            // これらの要素を一時的にリストから削除
            flowChartBlocks = flowChartBlocks.filterNot {
                it == BlockDataModel.REPEAT_START || it == BlockDataModel.REPEAT_END
            }

            // リストをシャッフル
            flowChartBlocks = flowChartBlocks.shuffled()

            // REPEAT_START と REPEAT_END を元の位置に戻す
            if (repeatStartExists) {
                flowChartBlocks = flowChartBlocks.toMutableList().apply {
                    add(repeatStartIndex, BlockDataModel.REPEAT_START)
                }
            }
            if (repeatEndExists) {
                flowChartBlocks = flowChartBlocks.toMutableList().apply {
                    add(repeatEndIndex, BlockDataModel.REPEAT_END)
                }
            }
        }
    }


    // 全種類のレッスンを取得するためのメソッド
    fun getAllLessons(): List<LessonDataModel> {
        return lessons
    }
}