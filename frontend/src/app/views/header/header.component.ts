import { Component, OnInit, EventEmitter, Output} from '@angular/core';
import { Router } from '@angular/router';
import { LoaderService } from 'src/app/service/loader.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Output() public sidenavToggle = new EventEmitter();
  constructor(public loaderService:LoaderService, private router:Router) { }

  ngOnInit(): void {
  }

  public onToggleSidenav = () =>  {
    this.sidenavToggle.emit();
  }

  logout = () => {
    localStorage.removeItem("token");
    this.router.navigate(['/login']);
  }
}
