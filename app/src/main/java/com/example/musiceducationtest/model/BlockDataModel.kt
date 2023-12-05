package com.example.musiceducationtest.model

data class BlockDataModel(
    val id: String, // ブロックのid
    val type: BlockType, // ブロックの種類（音楽、繰り返し初め、繰り返し終わり）
    val imageResId: Int, // ブロックに表示する画像のリソースID
    val musicResId: Int, // ブロックをタップした時に流れる音楽のリソースID
)

enum class BlockType {
    MUSIC, REPEAT_START, REPEAT_END
}