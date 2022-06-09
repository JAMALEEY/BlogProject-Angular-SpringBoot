import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { AuthService } from 'src/app/auth.service';
import { LoginPayload } from '../login-payload';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loginPayload!: LoginPayload;

  constructor(private authService: AuthService) {
    this.loginForm = new FormGroup(
      {
        username: new FormControl(),
        password: new FormControl()
      }
    );
    // instanciate the loginPayload
    this.loginPayload = {
      username:'',
      password:''

    }
   }

  ngOnInit(): void {
  }
  onSubmit(){
    // BAsically the payload allows us to send the data to backend
    this.loginPayload.username = this.loginForm.get('username')?.value;
    this.loginPayload.password = this.loginForm.get('password')?.value;

    //inject auth service into the component
    this.authService.login(this.loginPayload).subscribe(
      data => { data ? "Login successfull !" : "Sorry error while login to your account !!!" }
    )
  }

}
