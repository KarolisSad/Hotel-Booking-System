package viewModel;

public class ViewState {
  private String number;
  private boolean add;

  public ViewState(){
    this.number = null;
    this.add = false;
  }

  public String getNumber(){
    return number;
  }

  public void setNumber(String number){
    this.number = number;
  }

  public void removeNumber(){
    this.number = null;
  }

  public boolean isAdd(){
    return add;
  }

  public void setAdd(boolean add){
    this.add = add;
  }
}
