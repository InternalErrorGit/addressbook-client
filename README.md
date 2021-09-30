# Addressbook-Client Project
Repository: https://github.com/InternalErrorGit/addressbook-client
## Setup Guide
*In case something is not working as intended, you can always contact me on "gatzka@sowatec.com"*
### Setup in `IntelliJ Ultimate 2021.2.2` on Windows
* Download latest Javafx-sdk from https://gluonhq.com/products/javafx/
* Extract the zip wherever you want it


* Open Git Bash or other Terminal and navigate to an empty folder
* Execute `git clone https://github.com/InternalErrorGit/addressbook-client.git`
* Open Project with Intellij IDE
* Select the Run-Configuration with name **Client**
* Try to run the Application  **Fail is intended**
* Open the _Edit-Run-Configurations_ window in IntelliJ (Right top, next to the Build-Button)
* Open the folder ``javafx-sdk-17.0.0.1`` and head into ``/lib``
* Copy the path (example: ``C:\Program Files\Java\javafx-sdk-17.0.0.1\lib``)
* In the Run-Configurations Window in the VM-options, replace _javafxpathhere_ with the copied path to ``/lib``
* VM-Options should look something like this: ``--module-path "C:\Program Files\Java\javafx-sdk-17.0.0.1\lib" --add-modules javafx.controls,javafx.fxml``
* Hit ``Apply`` and ``Ok``
* Application should start now, if not, try to restart Intellij
* If it is still not working, please contact me i will answer ASAP

## Running the Application
Pre-defined user:
* Username: gatzka
* Password: zliapi

Make sure server and database are running
### Creating new Entries
In the context menu, select ``Action`` -> ``New`` -> (what you want to create)
**WARNING**:
* Client has no input validation, so make sure to not have empty fields.
* Creation of Person won't work if you have 0 Addresses
* Creation of Address won't work if you have 0 Cities


