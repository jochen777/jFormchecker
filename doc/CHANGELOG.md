# jFormChecker changelog

### 0.1.14

2017/08/26 
* Change implementation of SelectInput, so you can use more than one entry with the same key.


### 0.1.13

2017/08/10 
* Fix double id problem on CheckBoxInput
* Placeholder in TextArea
* convenience methods in TagAttributes


### 0.1.12

2017/05/09 
* DateInputSelectCompound with working setPresetValue and stable API  


### 0.1.11

2017/05/08 
* DateInputSelectCompound with working setPresetValue  


### 0.1.10

2017/04/27 
* More tests: Complete form submitted 
* Refactored tests, bump version for new integration feature (trim)
 


### 0.1.9

2017/04/12 
* Use "-" instead of "_" within class- and id-names
* Some refactorings
* Add ability to add custom tags to own elements 
 


### 0.1.8

2017/04/10 
* Allow setting form-method. 
* Delivering "tags" for non capable tag-engines like mustache. 
* Rearranged documentation.
* Introduce a Form-Surrounding Wrapper
* Remove some deprecated methods. 


### 0.1.7

2017/03/22 Fix: Radio-Label id. Fix: Avoid FormValidation running, if firstrun.


### 0.1.6

2017/03/05 Convenience methods for YearRange in DateSelect. Caching of "OK" Validation-result. Reverse order for years in date-select. Fix legacyDate get method on DateSelect. fix Checkbox Issue. Improve Email-Check. Bugfix on FormValidators (must return boolean now). New ValidationResult.failWithTranslated(str) convenience Method. Fix on setting a different Form-ID. New View Component to allow individual form-output in template. Documentation for individual html in template.

### 0.1.5

2017/02/02 Add StrongPassword validator. PasswordInput inherited from TextInput. Access to messages more safe. Convenience Selects (Month, year, day, gender), more consistent api on date-select, more documentation

### 0.1.4

2017/02/01 Add more documentation. Fix checkbox - required bug. Add getLegacyDate for DateInput. Fix required attribute in input fields

### 0.1.3 

2017/01/31 Convenient methods for select and radio. New Select-Date Input. Bugfix for Validates.

### 0.1.2 

2016/12/07 Builders now typesafe by using Generics

2016/12/11 Translated error-messages with higher prio than message-keys; message-key with jformchecker namespace

### 0.1.1 

2016/10/23 Simplified interfaces to request and session. This allows lambda style adapters for your framework. Add NumberInput and Number - Criterion, validationResult can hold an already translated message

### 0.1.0 

2016/10/20 Moved integration stuff to different project. Now with minimal dependencies. Took the test-suite to get really fast.

### 0.0.6 

2016/10/05 Control over required mark and label composition

### 0.0.5 

2016/10/04 Simplified Error-Handling, new wrapper for form-fields, working properties

### 0.0.4 

2016/10/03 More control over HTML rendering

### 0.0.3

2016/09/29 Major refactorings , error-Messages can be translated

### 0.0.2 

2016/09/24 Fix bug in min/max criteria

### 0.0.1 

2016/02/16 Initial release

