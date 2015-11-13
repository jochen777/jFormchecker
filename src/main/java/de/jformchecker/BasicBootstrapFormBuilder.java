package de.jformchecker;

public class BasicBootstrapFormBuilder extends GenericFormBuilder{

  @Override
  public TagAtrributes getFormAttributes() {
    TagAtrributes attributes = new TagAtrributes();
    return attributes;
  }

  @Override
  public Wrapper getWrapperForInput(FormCheckerElement elem) {
    return new Wrapper("", "");
  }

  @Override
  public TagAtrributes getLabelAttributes(FormCheckerElement elem) {
    return new TagAtrributes();
  }
  
}
