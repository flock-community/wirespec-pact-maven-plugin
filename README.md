# wirespec-pact-maven-plugin
Pact integration for wirespec 

## usage

```xml
<dependencies>
    <dependency>
        <groupId>community.flock.wirespec.plugins.pact</groupId>
        <artifactId>pact-maven-plugin</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```

```xml

<repositories>
    <repository>
        <id>oss.sonatype.org-snapshot</id>
        <url>http://oss.sonatype.org/content/repositories/snapshots</url>
        <releases><enabled>false</enabled></releases>
        <snapshots><enabled>true</enabled></snapshots>
    </repository>
</repositories>
```