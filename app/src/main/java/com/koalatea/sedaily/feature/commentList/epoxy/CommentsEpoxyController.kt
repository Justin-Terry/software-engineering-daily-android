package com.koalatea.sedaily.feature.commentList.epoxy

import android.graphics.Typeface
import com.airbnb.epoxy.TypedEpoxyController
import com.koalatea.sedaily.database.model.Comment

class CommentsEpoxyController(
        private val replyClickListener: (comment: Comment) -> Unit,
        private val upVoteClickListener: (comment:Comment) -> Unit
) : TypedEpoxyController<List<Comment>>() {

    override fun buildModels(comments: List<Comment>) {
        comments.forEach { comment ->
            commentEpoxyModelWithHolder {
                val imageHttpsUrl = comment.author.avatarUrl?.replace(Regex("^http://"), "https://")

                id(comment._id)
                authorImageUrl(imageHttpsUrl)
                authorName(comment.author.name ?: comment.author.username)
                comment(comment.content)
                date(comment.utcDateCreated)
                score(comment.score)
                replyClickListener { replyClickListener(comment) }
                upVoteClickListener { upVoteClickListener(comment) }

                if(comment.upvoted == false) {
                    textWeight(Typeface.NORMAL)
                } else {
                    textWeight(Typeface.BOLD)
                }

            }

            comment.replies?.forEach { reply ->
                replyEpoxyModelWithHolder {
                    val imageHttpsUrl = reply.author.avatarUrl?.replace(Regex("^http://"), "https://")

                    id(reply._id)
                    authorImageUrl(imageHttpsUrl)
                    authorName(reply.author.name ?: comment.author.username)
                    comment(reply.content)
                    date(reply.utcDateCreated)
                    score(reply.score)
                    upVoteClickListener { upVoteClickListener(reply) }

                    if(reply.upvoted == false) {
                        textWeight(Typeface.NORMAL)
                    } else {
                        textWeight(Typeface.BOLD)
                    }

                }
            }
        }
    }

}