package github.base

import org.kohsuke.github.GHPullRequestReviewState

class GHPullRequestWrapper extends Wrapper {
    def reviews() {
        listReviews()
    }

    def reviews(String str) {
        listReviews().findResults {
            it.state == GHPullRequestReviewState.valueOf(str) ? it : null
        }
    }

    def getReviews() {
        [:].withDefault { String key ->
            reviews(key)
        }
    }
}
