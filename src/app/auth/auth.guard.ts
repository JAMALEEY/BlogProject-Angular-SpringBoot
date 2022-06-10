import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../auth.service';


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
    constructor(private authService:AuthService,private router: Router){}

    canActivate(
        next: ActivatedRouteSnapshot,
/*         route: ActivatedRouteSnapshot, 
 */        state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    let isAuthenticated = this.authService.isAuthenticated();
    if(isAuthenticated){
        return true;
      } else {
        this.router.navigateByUrl('/login');
      }
        throw new Error('Method not implemented.');
    }

}
