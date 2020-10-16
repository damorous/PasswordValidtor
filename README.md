# PasswordValidtor

Write a password validation service, meant to be configurable via IoC (using dependency injection engine of your choice).  The service is meant to check a text string for compliance to any number of password validation rules.  The rules currently known are:

* Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.
* Must be between 5 and 12 characters in length.
* Must not contain any sequence of characters immediately followed by the same sequence.
