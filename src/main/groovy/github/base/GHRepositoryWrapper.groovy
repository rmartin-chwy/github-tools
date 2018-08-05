package github.base

import org.kohsuke.github.GHIssueState

class GHRepositoryWrapper extends Wrapper {
    def pullRequests(String str) {
        getPullRequests(GHIssueState.valueOf(str))
    }

    def getPullRequests() {
        [:].withDefault { String key ->
            pullRequests(key)
        }
    }
}
