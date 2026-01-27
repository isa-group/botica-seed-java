# botica-seed-java

Template project to facilitate implementing, compiling, and building Botica bots using Maven
and [botica-lib-java](https://github.com/isa-group/botica-lib-java/).

## 1. Initializing the template

You have two main options for setting up your bot project:

### 1.1. As a subdirectory in an existing Botica project (monorepo)

If you want to create this bot as a subdirectory to an existing Botica project (e.g., alongside your
`environment.yml` file), use the Botica Director's `init` command:

```bash
./botica-director init java <your-bot-directory-name>
```

Replace `<your-bot-directory-name>` with the desired folder name for your bot's project (e.g.,
`worker-bot`). This command will create the project files directly in that subdirectory.

### 1.2. As a separate repository

If you want to develop this bot in its own dedicated Git repository, click the
[_Use this
template_](https://github.com/new?template_name=botica-seed-java&template_owner=isa-group)
button on GitHub to create a new repository based on this one.

## 2. Modifying project files

Modify the `pom.xml` file, specifically:

1. The `<groupId>` and `<artifactId>` tags to match your project's naming conventions.
2. The `<mainClass>` property, pointing to your bot's entry point class (e.g.,
   `com.myorg.BotBootstrap`). This assumes you might rename the template classes or packages in
   `src/`.

## 3. Implementing your bot's logic

Implement your bot's logic by extending `BaseBot`. You can follow one
of [these examples](./src/main/java/com/example/examples) for inspiration.

> [!NOTE]
> Full project examples are also available, with their respective Java implementations. Check
> out [botica-infrastructure-fishbowl](https://github.com/isa-group/botica-infrastructure-fishbowl)
> or [botica-infrastructure-restest](https://github.com/isa-group/botica-infrastructure-restest).

## 4. Building and running

How you run your bot depends on how you set it up in Step 1.

### 4.1. If you initialized it as a subdirectory

The Botica Director can automatically build your bot from the source code before starting the
environment. You do not need to run any manual build scripts.

In your `environment.yml` file, use the `build` property to point to your bot's directory:

```yml
bots:
  my-java-bot:
    # The 'build' property tells the Director to build a Docker image from the
    # project located at the specified relative path before running it.
    build: "./worker-bot"
    replicas: 1
    lifecycle:
      type: reactive
```

When you run `./botica-director`, it will detect the `build` property, execute the build process
defined in the project's Dockerfile, and then launch the container.

### 4.2. If you are using a separate repository

If you are working in a standalone repository, you need to manually build the Docker image so it is
available to your Docker daemon. You can tag it however you like (e.g.,
`my-org/my-bot:latest`):

```bash
docker build -t my-org/my-bot:latest .
```

In your `environment.yml` file, use the `image` property to reference the tag you just created:

```yml
bots:
  my-java-bot:
    # The 'image' property tells the Director to look for an existing
    # Docker image with this tag.
    image: "my-org/my-bot:latest"
    replicas: 1
    lifecycle:
      type: reactive
```
