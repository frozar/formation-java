package factory.example.factory;

import factory.example.buttons.Button;
import factory.example.buttons.WindowsButton;

public class WindowsDialog extends Dialog {

  @Override
  public Button createButton() {
    return new WindowsButton();
  }

}
