# jFormChecker documentation

Check out, how to use jFormchecker in your projects. 


## Concept

Implement your webforms with these ingredients:

* A FormCheckerForm-Class
* A Controller with a Formchecker
* An indidividual FormBuilder Class
* A Template

The FormCheckerForm-Class defines the form with the form-elements.
There are a number of prebuild form-elements that should be enough to do the basic work.
Each form-element can have validations. The form can have a form-validator too. 

The FormCheckerForm is passed to the Formchecker. The formchecker prefills the form, triggers the validators and decides, if it is valid.

The Formbuilder renders the form to html. 

The template just makes a call to the formbuilder which returns the html for the form.

## The WebForm

