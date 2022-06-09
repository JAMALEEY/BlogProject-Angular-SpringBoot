import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';
import { LoginPayload } from '../login-payload';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loginPayload!: LoginPayload;

  constructor(private authService: AuthService, private router:Router) {
    this.loginForm = new FormGroup({
      username: new FormControl(),
      password: new FormControl(),
    });
    // instanciate the loginPayload
    this.loginPayload = {
      userName: '',
      password: '',
    };
  }

  ngOnInit(): void {}
  onSubmit() {
    // BAsically the payload allows us to send the data to backend
    this.loginPayload.userName = this.loginForm.get('username')?.value;
    this.loginPayload.password = this.loginForm.get('password')?.value;

    this.authService.login(this.loginPayload).subscribe(data => {
      if(data) {
        console.log('login success');
        this.router.navigateByUrl('/home');
      } else {
        console.log('Login fail ... Retry ');
      }
    })
  }
}
