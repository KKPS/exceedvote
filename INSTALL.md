# exceedvote Installation Guide

## Run via .war file

Download `/dist/exceedvote-1.0-SNAPSHOT.war` (exceedvote WAR build file) and running on your Tomcat server. [Using Tomcat](http://se.cpe.ku.ac.th/wiki/index.php/Using_Tomcat) can explain you how to setup Tomcat server and run your .war file

This build of exceedvote is setup for using on se.cpe.ku.ac.th server. If you want to use exceedvote in your own setting. See below.

## Run via Play! Framework

First, You need to [Install Play! Framework](http://www.playframework.org/documentation/2.0.4/Installing). When you are done. You can use Play! command on your Play! project directory.

Then use:

    play run
    
To run Play! project. When you run project, Go to `http://localhost:9000` for your using Play! application.

### Database Setup

To run your project. You need to setup your database for your Play! application. Locate to `conf/application.conf`.

In `# Database configuration`, If you want to use memory database uncomment these code:

    db.default.driver=org.h2.Driver
    db.default.url="jdbc:h2:mem:play"
    
If you want to use MySQL connection use these:

    db.default.driver=com.mysql.jdbc.Driver
    db.default.url="jdbc:mysql: -- Your database URL -- "
    db.default.user= -- Your database login --
    db.default.password= -- You database password --

### Initial Data Setup

First time you run exceedvote. It's automatically create table in your database and insert some data for you.

These datas come from `conf/initial-data.yml` that's you can edit by yourself. For example:

    - !!models.User
        username:        sonnyhonny
        password:        exceed
        name:            Kanokphol Techarattanaprasert
        isAdmin:         false
        firstLogin:      true
        role:
            !!models.Role
              id: 1
              
Will add User with username: sonnyhonny, password: exceed....

### Create .war file from Play! Framework Project

Simply use this command:

    play package
    
And Play! with package .war file for you in your project directory `/target`
