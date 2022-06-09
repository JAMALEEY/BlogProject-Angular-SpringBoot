import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {
  addPostForm?: FormGroup;
  title = new FormControl('');
  body = new FormControl('');

  constructor() { 
    this.addPostForm = new FormGroup(
      {
        title: this.title,
        body: this.body
      }
    )
  }

  ngOnInit(): void {
  }

  addPost() {

  }

}
