package factory.example.factory;

import factory.example.buttons.Button;
import factory.example.buttons.HtmlButton;

public class HtmlDialog extends Dialog {

  @Override
  public Button createButton() {
    return new HtmlButton();
  }

}
