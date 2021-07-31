rootProject.name = "MinecraftMediaLibrary"

include("api")
include("v1_16_R3")
include("main")
include("lib")

findProject("api")?.name = "minecraftmedialibrary-api"
findProject("main")?.name = "minecraftmedialibrary"
findProject("lib")?.name = "minecraftmedialibrary-lib"


