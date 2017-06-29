#Creation

-create a absic maven project
-add the following code in your pom.xml

<repositories>
    <repository>
        <id>sonatype</id>
        <name>Sonatype Repository</name>
        <url>http://oss.sonatype.org/content/groups/public</url>
       <snapshots>
          <enabled>true</enabled>
       </snapshots>
    </repository>
    <repository>
        <id>jboss</id>
        <name>JBoss Repository</name>
        <url>http://repository.jboss.org/nexus/content/groups/public-jboss/</url>
    </repository>
  </repositories>
  <pluginRepositories>
    <pluginRepository>
        <id>sonatype</id>
        <name>Sonatype Repository</name>
        <url>http://oss.sonatype.org/content/groups/public</url>
       <snapshots>
          <enabled>true</enabled>
       </snapshots>
    </pluginRepository>
  </pluginRepositories>

-run a basic mvn clean install

-then in a new terminal, you should be able to
mvn org.andromda.maven.plugins:andromdapp-maven-plugin:3.5-SNAPSHOT:generate

-i add several problems due to Papyrus uncomaptible uml with 3.4
-I switch to 3.5-S
-i have selcted je22, spring, both (rest=ws), json ,axis 2
