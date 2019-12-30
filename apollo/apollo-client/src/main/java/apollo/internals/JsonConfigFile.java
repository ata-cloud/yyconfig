package apollo.internals;


import framework.apollo.core.enums.ConfigFileFormat;

/**
 * @author Jason Song(song_s@ctrip.com)
 */
public class JsonConfigFile extends PlainTextConfigFile {
  public JsonConfigFile(String namespace,
                        ConfigRepository configRepository) {
    super(namespace, configRepository);
  }

  @Override
  public ConfigFileFormat getConfigFileFormat() {
    return ConfigFileFormat.JSON;
  }
}
