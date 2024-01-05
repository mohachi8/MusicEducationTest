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

    BLOCK01T_T("block01t", BlockType.MUSIC, R.drawable.up, R.raw.ttls_01),
    BLOCK02T_T("block02t", BlockType.MUSIC, R.drawable.down, R.raw.ttls_02),
    BLOCK03T_T("block03t", BlockType.MUSIC, R.drawable.down, R.raw.ttls_03),
    BLOCK04T_T("block04t", BlockType.MUSIC, R.drawable.down, R.raw.ttls_04),
    BLOCK05T_T("block05t", BlockType.MUSIC, R.drawable.down, R.raw.ttls_05),
    BLOCK06T_T("block06t", BlockType.MUSIC, R.drawable.down, R.raw.ttls_06),
    BLOCK07T_T("block07t", BlockType.MUSIC, R.drawable.down, R.raw.ttls_07),
    BLOCK08T_T("block08t", BlockType.MUSIC, R.drawable.down, R.raw.ttls_08),
    BLOCK09T_T("block09t", BlockType.MUSIC, R.drawable.up, R.raw.ttls_09),
    BLOCK10T_T("block10t", BlockType.MUSIC, R.drawable.down, R.raw.ttls_10),
    BLOCK11T_T("block11t", BlockType.MUSIC, R.drawable.down, R.raw.ttls_11),
    BLOCK12T_T("block12t", BlockType.MUSIC, R.drawable.down, R.raw.ttls_12),

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

    BLOCK01S("block01s", BlockType.MUSIC, R.drawable.sea_01, R.raw.sea_01),
    BLOCK02S("block02s", BlockType.MUSIC, R.drawable.sea_02, R.raw.sea_02),
    BLOCK03S("block03s", BlockType.MUSIC, R.drawable.sea_03, R.raw.sea_03),
    BLOCK04S("block04s", BlockType.MUSIC, R.drawable.sea_04, R.raw.sea_04),
    BLOCK05S("block05s", BlockType.MUSIC, R.drawable.sea_05, R.raw.sea_05),
    BLOCK06S("block06s", BlockType.MUSIC, R.drawable.sea_06, R.raw.sea_06),
    BLOCK07S("block07s", BlockType.MUSIC, R.drawable.sea_07, R.raw.sea_07),
    BLOCK08S("block08s", BlockType.MUSIC, R.drawable.sea_08, R.raw.sea_08),

    BLOCK01Sa("block01sa", BlockType.MUSIC, R.drawable.sakura_01, R.raw.sakura_01),
    BLOCK011Sa("block01sa", BlockType.MUSIC, R.drawable.sakura_01, R.raw.sakura_01),
    BLOCK012Sa("block01sa", BlockType.MUSIC, R.drawable.sakura_01, R.raw.sakura_01),
    BLOCK013Sa("block01sa", BlockType.MUSIC, R.drawable.sakura_01, R.raw.sakura_01),
    BLOCK02Sa("block02sa", BlockType.MUSIC, R.drawable.sakura_02, R.raw.sakura_02),
    BLOCK03Sa("block03sa", BlockType.MUSIC, R.drawable.sakura_03, R.raw.sakura_03),
    BLOCK04Sa("block04sa", BlockType.MUSIC, R.drawable.sakura_04, R.raw.sakura_04),
    BLOCK05Sa("block05sa", BlockType.MUSIC, R.drawable.sakura_05, R.raw.sakura_05),
    BLOCK06Sa("block06sa", BlockType.MUSIC, R.drawable.sakura_06, R.raw.sakura_06),
    BLOCK07Sa("block07sa", BlockType.MUSIC, R.drawable.sakura_07, R.raw.sakura_07), ;
}

// ブロックのタイプ：音楽を再生するブロックか、繰り返し始めか、繰り返し終わりか
enum class BlockType {
    MUSIC, REPEAT_START, REPEAT_END
}