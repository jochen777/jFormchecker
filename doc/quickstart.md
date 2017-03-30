# Quickstart


## Add dependency

Add this dependency to your project:

```xml
...
<dependency>
    <groupId>de.cyclon-softworx</groupId>
    <artifactId>jformchecker</artifactId>
    <version>0.1.7</version> <!-- Please check for the latest version on maven central or in the changelog! -->
</dependency>
...
```

## Define a form

Write a class, that extends FormCheckerForm and add some fields to it:


```Java

public class MyForm extends FormCheckerForm {

  @Override
  public void init() {
    // Textfield for firstname
    add(TextInput.build("firstname")
      .setDescription("Firstname"));

    // Textfield with email validation and required
    add(TextInput.build("email")
      .setDescription("Email")
      .setRequired()
      .setCriterias(Criteria.emailAddress()));
    
    // Add more FormElements here...
  }
}
```


## Controller

Write a controller, that uses this form: (Here Spring MVC)

```Java

  @RequestMapping("/form")
  public String intro(HttpServletRequest request, Model model) {
    FormChecker fc = FormChecker.build(
        (key) -> request.getParameter(key), // pass the request-params via lambda 
        new MyForm()  // the form that you wrote (see "Define a form")
      )
     .run();  // let the form validation/prefillment and html rendering run
    
    model.addAttribute("fc", fc.getView()); // add the formchecker object to the model
    if (fc.isValidAndNotFirstRun()) {   // check if the form was submitted and is valid
      System.out.println("Valid email from form:"  + fc.getValue("email"));   // if everything was okay, we can get the values from the form
    }
    
    return "index"; // the template, that renders the form
  }

```


## Template

Output the form within your template. (index.html)


```html
...
<h1>The form</h1>

<!-- start the form -->
${fc.form}
<!-- end form -->

...
```


## Output

![Form Example](form_example.png "Form example output")


## Go on

[Documentation](start.md)

[Read about the concepts](concept.md)

[Read the changelog](CHANGELOG.md)
