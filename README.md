/*    This file is part of xinyi3_notes.
	
	Copyright (C) 2014 Xin Yi Wang xinyi3@ualberta.ca
	
    xinyi3-notes is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    xinyi3-notes is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with xinyi3_notes.  If not, see <http://www.gnu.org/licenses/>.
    
    */


Android TODO list application. It is able to record TODO items, check items are done, archive items, browse visible and archived TODO options, Email TODO items and Archived Items and get a summary.

Most of the project is made using the Student-Picker video series by Abram Hindle as a reference guide.
https://www.youtube.com/watch?v=5PPD0ncJU1g  26/09/2014

Some other sources used as erference include :
Katerina Zamani, http://examples.javacodegeeks.com/android/core/email/android-sending-email-example/  26/09/2014
fiXedd, http://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application 26/09/2014

Note, upon first cloning the app you might need to click on Project in Eclipse and Clean.

In-App contorlls:
	-Click settings button (top right) to navigate between TODO list, Archive, summary, and Email
	-type in the name of your item and click Add button located on the bottom right
	-Long Click on the items in either TODO list or Archived List to delete the item or move it into archived/Todo
	-Click on the items in TODO list to check/uncheck them
	-In the Summary page, press Give Me a Summary to look at varius data.
	-Inside email, press: 	Email All Todo Items will Email all the items in TodoList
				Email Selected Archived Items will navigate to a different page to let the user select 						items they wish to email.
				Email Selected Todo Items will navigate to a different page to let the user select 						items they wish to email.
				Email All Todo items Including Archived will email everything.
				
