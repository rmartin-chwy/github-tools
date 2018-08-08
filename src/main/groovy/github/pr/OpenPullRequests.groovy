package github.pr

import github.base.GroovyGitHub

GroovyGitHub.session {
    organization('Chewy-Inc') {
        repository('backoffice') {
            pullRequests.OPEN.each {
                println "Pull request ${it.number} by ${it.user.name ?: it.user.login}: ${it.title}"
                // GET /repos/:owner/:repo/pulls/:number/reviews
                it.reviews.APPROVED.each {
                    println "\tApproved by: ${it.user.name ?: it.user.login}"
                }
            }
        }
    }
}

