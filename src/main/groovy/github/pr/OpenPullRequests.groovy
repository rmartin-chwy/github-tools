package github.pr

import github.base.GroovyGitHub

GroovyGitHub.session {
    organization('Chewy-Inc') {
        repository('backoffice') {
            pullRequests.OPEN.each {
                println "Pull request by ${it.user.name ?: it.user.login}: ${it.title}"
            }
        }
    }
}

