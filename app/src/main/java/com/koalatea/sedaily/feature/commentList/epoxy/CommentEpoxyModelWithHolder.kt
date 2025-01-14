package com.koalatea.sedaily.feature.commentList.epoxy

import android.graphics.Typeface
import android.widget.Button
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.koalatea.sedaily.R

@EpoxyModelClass(layout = R.layout.view_holder_comment)
abstract class CommentEpoxyModelWithHolder : BaseCommentEpoxyModelWithHolder<CommentHolder>() {

    @EpoxyAttribute lateinit var replyClickListener: () -> Unit
    @EpoxyAttribute lateinit var upVoteClickListener: () -> Unit
    @EpoxyAttribute var textWeight: Int = Typeface.NORMAL


    override fun bind(holder: CommentHolder) {
        super.bind(holder)

        holder.replayButton.setOnClickListener { replyClickListener() }
        holder.upVoteButton.setOnClickListener { upVoteClickListener() }

        holder.upVoteButton.setTypeface(null, textWeight)

    }

}

class CommentHolder : BaseCommentHolder() {
    val replayButton by bind<Button>(R.id.replyButton)
    val upVoteButton by bind<TextView>(R.id.commentUpvoteButton)
}