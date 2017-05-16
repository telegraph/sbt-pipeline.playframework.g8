#### Label: ####
_[bug, improvement, feature]_
#### What's this PR do?
_Sort description of the purpose of the changes in the pull request_
######Example: 
Add cloudformation templates for NAM ECS service. This will create two ECS services, one for warmer, one for worker, and a Redis replication group.
Used the same Redis version and instance type as the current deployment.
The way we startup the application has changed. Basically we will pass -Dmode=worker/warmer. This is documented in the readMe file.
A docker file was also created, which takes a "MODE" build argument. This is used to update the new relic config and set the jvm properties for the spring boot app.
Used official openjdk 8 alpine based image https://hub.docker.com/_/openjdk/ https://github.com/docker-library/openjdk/blob/4e39684901490c13eaef7892c44e39043d7c4bed/8-jre/alpine/Dockerfile
#### Where should the reviewer start?
_Guide for the reviewer or where to start_
######Example: 
Start with the template.json file in each static and dynamic infrastructure folders.
#### What tests were added?
_To indicate if any test was added_
######Example:
None
#### Any background context you want to provide?
_To indicate context that the reviews must now_
######Example:
This is part of the work being done to migrate NAM to ECS.
#### Are there other tickets linked to this?
######Example:
https://jira.aws.telegraph.co.uk/browse/PLAT-880
#### Outstanding questions?
Might need tweaks to cpu/mem reservation.
######Example:
Might need tweaks to cpu/mem reservation.
#### Definition of Done:
- [ ] Is there appropriate test coverage?
- [ ] Does the confluence need to be updated? https://jira.aws.telegraph.co.uk/browse/PLAT-926
- [ ] Does this add new dependencies? If so, have the healthchecks been updated?
- [ ] Any Deployment changes we need to be aware of? New infrastructure? Changes to existing components? Service will be moved to Jenkins https://jira.aws.telegraph.co.uk/browse/PLAT-862
- [ ] Is there appropriate logging included?
- [ ] Does it imply any kind of data migration? If so what are the steps for that?
- [ ] Is the Documentation been updated, if needed?
