package com.github.edgarespina.handlebars.io;

import static org.parboiled.common.Preconditions.checkArgument;
import static org.parboiled.common.Preconditions.checkNotNull;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.github.edgarespina.handlebars.TemplateLoader;

/**
 * Load templates from the file system. A base path must be specified at
 * creation time. The base path serve as template repository.
 *
 * @author edgar.espina
 * @since 0.1.0
 */
public class FileTemplateLoader extends TemplateLoader {

  /**
   * Creates a new {@link FileTemplateLoader}.
   *
   * @param basedir The base directory. Required.
   * @param suffix The view suffix. Required.
   */
  public FileTemplateLoader(final File basedir, final String suffix) {
    checkNotNull(basedir, "The base dir is required.");
    checkArgument(basedir.exists(), "File not found: %s", basedir);
    checkArgument(basedir.isDirectory(), "A directory is required: %s",
        basedir);
    setPrefix(basedir.toString());
    setSuffix(suffix);
  }

  /**
   * Creates a new {@link FileTemplateLoader}.
   *
   * @param basedir The base directory. Required.
   */
  public FileTemplateLoader(final File basedir) {
    this(basedir, DEFAULT_SUFFIX);
  }

  /**
   * Creates a new {@link FileTemplateLoader}.
   *
   * @param basedir The base directory. Required.
   * @param suffix The view suffix. Required.
   */
  public FileTemplateLoader(final String basedir, final String suffix) {
    setPrefix(basedir);
    setSuffix(suffix);
  }

  /**
   * Creates a new {@link FileTemplateLoader}.
   *
   * @param basedir The base directory. Required.
   */
  public FileTemplateLoader(final String basedir) {
    this(basedir, DEFAULT_SUFFIX);
  }

  @Override
  protected Reader read(final String location) throws IOException {
    File file = new File(location);
    if (!file.exists()) {
      return null;
    }
    return new FileReader(file);
  }

}
