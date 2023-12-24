package com.example.musiceducationtest.model

import com.example.musiceducationtest.R

enum class BlockDataModel(
    val id: String, // ブロックのid
    val type: BlockType, // ブロックのタイプ
    val imageResId: Int, // ブロックに表示する画像
    val musicResId: Int?// ブロックの音楽（繰り返しに関するブロックはnullになる）
) {
    REPEAT_START("repeatStart", BlockType.REPEAT_START, R.drawable.repeat_start, null),
    REPEAT_END("repeatEnd", BlockType.REPEAT_END, R.drawable.repeat_end, null),

    BLOCK01T_T("block01t", BlockType.MUSIC, R.drawable.ttls_01, R.raw.ttls_01),
    BLOCK02T_T("block02t", BlockType.MUSIC, R.drawable.ttls_02, R.raw.ttls_02),
    BLOCK03T_T("block03t", BlockType.MUSIC, R.drawable.ttls_03, R.raw.ttls_03),
    BLOCK04T_T("block04t", BlockType.MUSIC, R.drawable.ttls_04, R.raw.ttls_04),
    BLOCK05T_T("block05t", BlockType.MUSIC, R.drawable.ttls_05, R.raw.ttls_05),
    BLOCK06T_T("block06t", BlockType.MUSIC, R.drawable.ttls_06, R.raw.ttls_06),
    BLOCK07T_T("block07t", BlockType.MUSIC, R.drawable.ttls_07, R.raw.ttls_07),
    BLOCK08T_T("block08t", BlockType.MUSIC, R.drawable.ttls_08, R.raw.ttls_08),
    BLOCK09T_T("block09t", BlockType.MUSIC, R.drawable.ttls_09, R.raw.ttls_09),
    BLOCK10T_T("block10t", BlockType.MUSIC, R.drawable.ttls_10, R.raw.ttls_10),
    BLOCK11T_T("block11t", BlockType.MUSIC, R.drawable.ttls_11, R.raw.ttls_11),
    BLOCK12T_T("block12t", BlockType.MUSIC, R.drawable.ttls_12, R.raw.ttls_12),

    BLOCK01T_M("block01t", BlockType.MUSIC, R.drawable.midi_01, R.raw.ttls_01),
    BLOCK02T_M("block02t", BlockType.MUSIC, R.drawable.midi_02, R.raw.ttls_02),
    BLOCK03T_M("block03t", BlockType.MUSIC, R.drawable.midi_03, R.raw.ttls_03),
    BLOCK04T_M("block04t", BlockType.MUSIC, R.drawable.midi_04, R.raw.ttls_04),
    BLOCK05T_M("block05t", BlockType.MUSIC, R.drawable.midi_05, R.raw.ttls_05),
    BLOCK06T_M("block06t", BlockType.MUSIC, R.drawable.midi_06, R.raw.ttls_06),
    BLOCK09T_M("block09t", BlockType.MUSIC, R.drawable.midi_01, R.raw.ttls_09),
    BLOCK10T_M("block10t", BlockType.MUSIC, R.drawable.midi_02, R.raw.ttls_10),
    BLOCK11T_M("block11t", BlockType.MUSIC, R.drawable.midi_03, R.raw.ttls_11),
    BLOCK12T_M("block12t", BlockType.MUSIC, R.drawable.midi_04, R.raw.ttls_12),

    BLOCK01T("block01t", BlockType.MUSIC, R.drawable.star_01, R.raw.star_01),
    BLOCK02T("block02t", BlockType.MUSIC, R.drawable.star_02, R.raw.star_02),
    BLOCK03T("block03t", BlockType.MUSIC, R.drawable.star_03, R.raw.star_03),
    BLOCK04T("block04t", BlockType.MUSIC, R.drawable.star_04, R.raw.star_04),
    BLOCK05T("block05t", BlockType.MUSIC, R.drawable.star_05, R.raw.star_05),
    BLOCK06T("block06t", BlockType.MUSIC, R.drawable.star_06, R.raw.star_06),
    BLOCK09T("block09t", BlockType.MUSIC, R.drawable.star_01, R.raw.star_01),
    BLOCK10T("block10t", BlockType.MUSIC, R.drawable.star_02, R.raw.star_02),
    BLOCK11T("block11t", BlockType.MUSIC, R.drawable.star_03, R.raw.star_03),
    BLOCK12T("block12t", BlockType.MUSIC, R.drawable.star_04, R.raw.star_04),

    BLOCK01S("block01s", BlockType.MUSIC, R.drawable.yellow, R.raw.ttls_01),
    BLOCK02S("block02s", BlockType.MUSIC, R.drawable.skyblue, R.raw.ttls_02),
    BLOCK03S("block03s", BlockType.MUSIC, R.drawable.orange, R.raw.ttls_03),
    BLOCK04S("block04s", BlockType.MUSIC, R.drawable.purple, R.raw.ttls_04),
    BLOCK05S("block05s", BlockType.MUSIC, R.drawable.red, R.raw.ttls_05),
    BLOCK06S("block06s", BlockType.MUSIC, R.drawable.green, R.raw.ttls_06),
    BLOCK07S("block07s", BlockType.MUSIC, R.drawable.pink, R.raw.ttls_07),
    BLOCK08S("block08s", BlockType.MUSIC, R.drawable.blue, R.raw.ttls_08), ;
}

// ブロックのタイプ：音楽を再生するブロックか、繰り返し始めか、繰り返し終わりか
enum class BlockType {
    MUSIC, REPEAT_START, REPEAT_END
}