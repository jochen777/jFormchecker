package de.jformchecker;

public class BasicBootstrapFormBuilder extends GenericFormBuilder{

  @Override
  public TagAttributes getFormAttributes() {
    TagAttributes attributes = new TagAttributes();
    return attributes;
  }

  @Override
  public Wrapper getWrapperForInput(FormCheckerElement elem) {
    return new Wrapper("", "");
  }

  @Override
  public TagAttributes getLabelAttributes(FormCheckerElement elem) {
    return new TagAttributes();
  }
  
}
