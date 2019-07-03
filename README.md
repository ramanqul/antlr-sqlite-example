# How to configure antlr4 with maven?
---
you have to add antlr4-maven-plugin like:

<plugin>
    <groupId>org.antlr</groupId>
    <artifactId>antlr4-maven-plugin</artifactId>
    <version>4.3</version>
	<executions>
		<execution>
			<goals>
				<goal>antlr4</goal>
			</goals>
			<configuration>
				<sourceDirectory>${antlr.dir}</sourceDirectory>
				<listener>true</listener>
				<visitor>true</visitor>
				<arguments>
					<argument>-package</argument>
					<argument>com.example.antlr4</argument>
				</arguments>
			</configuration>
		</execution>
	</executions>
</plugin>

After that place your SomeGrammar.g4 file in src/main/antl4/com/example/antlr4/SomeGrammar.g4
Also you have to provide package in the plugin configuration using arguments.

#How to import project into IDEA?
---
1. Import project as Maven project into your IDEA.
2. Add configuration: Maven -> command line -> place: generate-sources
3. Run your configuration.
4. Run Main.java file to see demo results.


