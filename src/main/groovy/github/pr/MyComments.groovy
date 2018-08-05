package github.pr

import github.base.GroovyGitHub

GroovyGitHub.session {
    organization('Chewy-Inc') {
        repository('backoffice') {
            pullRequests.CLOSED.each {
                def myComments = it.listReviewComments().findResults {
                    it.user.login == "rmartin7c" ? it : null
                }.sort { it.createdAt }

                if (myComments) {
                    println "\nPull request by ${it.user.name ?: it.user.login}: ${it.title}"
                    myComments.each {
                        println "Comment on ${it.createdAt}: \n${it.body}"
                    }
                }

            }
        }
    }
}
