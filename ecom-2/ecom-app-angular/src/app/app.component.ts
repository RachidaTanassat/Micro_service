import {Component, OnInit} from '@angular/core';
import {KeycloakService} from "keycloak-angular";
import {KeycloakProfile} from "keycloak-js/lib/keycloak";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  public profile!: KeycloakProfile;
  title = 'ecom-app-angular';

  constructor(public keycloakService: KeycloakService) {
  }
  async handleLogin() {
    this.keycloakService.login({
      redirectUri: window.location.origin
    });
  }

   handleLogout() {
    this.keycloakService.logout(window.location.origin)

  }

  ngOnInit(): void {
    if(this.keycloakService.isLoggedIn()){
      this.keycloakService.loadUserProfile().then(profile=>{
        this.profile = profile;
      });
    }
  }
}
