package com.example.musiceducationtest.model

import com.example.musiceducationtest.R

enum class BlockDataModel(
    val id: String,
    val type: BlockType,
    val imageResId: Int,
    val musicResId: Int
) {
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

    BLOCK01T("block01t", BlockType.MUSIC, R.drawable.blue, R.raw.ttls_01),
    BLOCK02T("block02t", BlockType.MUSIC, R.drawable.gray, R.raw.ttls_02),
    BLOCK03T("block03t", BlockType.MUSIC, R.drawable.green, R.raw.ttls_03),
    BLOCK04T("block04t", BlockType.MUSIC, R.drawable.orange, R.raw.ttls_04),
    BLOCK05T("block05t", BlockType.MUSIC, R.drawable.pink, R.raw.ttls_05),
    BLOCK06T("block06t", BlockType.MUSIC, R.drawable.purple, R.raw.ttls_06),
    BLOCK07T("block07t", BlockType.MUSIC, R.drawable.red, R.raw.ttls_07),
    BLOCK08T("block08t", BlockType.MUSIC, R.drawable.sand, R.raw.ttls_08),
    BLOCK09T("block09t", BlockType.MUSIC, R.drawable.skyblue, R.raw.ttls_09),
    BLOCK10T("block10t", BlockType.MUSIC, R.drawable.summer, R.raw.ttls_10),
    BLOCK11T("block11t", BlockType.MUSIC, R.drawable.yellow, R.raw.ttls_11),
    BLOCK12T("block12t", BlockType.MUSIC, R.drawable.yellowgreen, R.raw.ttls_12),

    BLOCK01S("block01s", BlockType.MUSIC, R.drawable.yellow, R.raw.ttls_01),
    BLOCK02S("block02s", BlockType.MUSIC, R.drawable.skyblue, R.raw.ttls_02),
    BLOCK03S("block03s", BlockType.MUSIC, R.drawable.orange, R.raw.ttls_03),
    BLOCK04S("block04s", BlockType.MUSIC, R.drawable.purple, R.raw.ttls_04),
    BLOCK05S("block05s", BlockType.MUSIC, R.drawable.red, R.raw.ttls_05),
    BLOCK06S("block06s", BlockType.MUSIC, R.drawable.green, R.raw.ttls_06),
    BLOCK07S("block07s", BlockType.MUSIC, R.drawable.pink, R.raw.ttls_07),
    BLOCK08S("block08s", BlockType.MUSIC, R.drawable.blue, R.raw.ttls_08), ;
}

enum class BlockType {
    MUSIC, REPEAT_START, REPEAT_END
}