package com.microsoft.wikibrowser.models;

import lombok.Data;

/**
 * Created by rupam.ghosh on 11/06/16.
 */
@Data
public class Page {
  long pageid;
  int ns;
  String title;
  int index;
  Thumbnail thumbnail;
}
