import { Component, OnInit } from '@angular/core';
import { WorkService } from '../services/work.service';
import { NewWorkDTO } from '../model/NewWorkDTO';
import { AuthService } from '../auth/service/auth.service';
import { User } from '../model/User';
import { SearchService } from '../services/search.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-new-work',
  templateUrl: './add-new-work.component.html',
  styleUrls: ['./add-new-work.component.css']
})
export class AddNewWorkComponent implements OnInit {

  newWorkDTO: NewWorkDTO = new NewWorkDTO();
  fileUrl: string;
  fileToUpload: File;
  user: User = new User();
  naucneOblasti = [];
  forma : boolean = true;
  casopisi = [];
  screcs = [];
  scodabrani;
  mltrecs = [];
  mltodabrani;
  georecs = [];
  geoodabrani;
  recOdakle = 0;
  wid;
  constructor(private workService: WorkService, private authService: AuthService, private searchService : SearchService, private router : Router) { 
    this.workService.getujNaucneOblasti().subscribe(data => {
      this.naucneOblasti = data;
      console.log(this.naucneOblasti);
    });
    this.workService.getujCasopise().subscribe(data => {
      this.casopisi = data;
      console.log(this.casopisi);
    });

  }

  

  ngOnInit() {
  }

  onSubmit(value, form) {
    console.log(this.newWorkDTO);
    this.workService.uploadFile(this.fileToUpload).subscribe(response => {

      console.log(response);
      this.newWorkDTO.putanja = response;

      this.authService.getLogged().subscribe(logged => {
        this.user = logged;
        console.log(logged);
        this.newWorkDTO.autorId = logged.id;
        let x = this.workService.postNewWork(this.newWorkDTO);
        x.subscribe(
          res => {
            console.log(res);
            this.wid = res.id;
            this.searchService.getRevsBySC(res.id).subscribe(data => {
              this.screcs = data;
            });
            this.searchService.searchByMoreLikeThis(res.id).subscribe(data => {
              this.mltrecs = data;
            });
            this.searchService.searchByGeoSpacing(res.id).subscribe(data => {
              this.georecs = data;
            });

            this.forma = false;
          },
          err => {
            console.log("Error occured");
            console.log(err.error);
          }
        );
      });
      // }

    });

  }

  onChange() {
    // alert(this.recOdakle);
  }

  submitReviewers() {
    if(this.recOdakle == 1) {
      console.log(this.scodabrani);
      this.workService.postujRecenzente(this.wid, this.scodabrani).subscribe(data => {
        this.router.navigate(["/"]);
      });
    }
    if(this.recOdakle == 2) {
      console.log(this.mltodabrani);
      this.workService.postujRecenzente(this.wid, this.mltodabrani).subscribe(data => {
        this.router.navigate(["/"]);
      });
    }
    if(this.recOdakle == 3) {
      console.log(this.geoodabrani);
      this.workService.postujRecenzente(this.wid, this.geoodabrani).subscribe(data => {
        this.router.navigate(["/"]);
      });
    }
    
  }


  handleFileInput(file: FileList) {
    this.fileToUpload = file.item(0);
    var reader = new FileReader();
    //console.log(this.fileToUpload)
    reader.onload = (event: any) => {
      this.fileUrl = event.target.result;//ovo ne radi za pdf nesto
    }
    reader.readAsDataURL(this.fileToUpload);
    console.log("URL " + this.fileUrl);
    console.log("file " + this.fileToUpload.name);
  }

}
