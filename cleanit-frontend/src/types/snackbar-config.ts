export class SnackbarConfig {
  show: boolean;
  message: string;
  color: string;

  constructor(show: boolean, message: string, color: string) {
    this.show = show;
    this.message = message;
    this.color = color;
  }
}
