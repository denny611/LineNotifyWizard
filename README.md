# **Line Notify wizard Design**

About:

A notify manger to help manger line notify

Problem statement:

Line is a public im in Taiwan, however sometimes line notify could be disturbing.

Features/benefits:

Use the android notify access to read / filter line notify and support mute line messages by schedule.
![LineNotifyWizardClassDiagram](https://github.com/denny611/LineNotifyWizard/assets/129499761/e0f1744a-0f88-48f8-a9c5-1e3b5739de56)



      
# NotifyListenerService
Listen to all line notify and send notify message to MessageReoisitory

# MessageReoisitory
Storage to cache all Line notify

# AlarmManger
Alarm mange to wake up next message update

# NotifyManger
A class to filter Notify by keyword and (or) mute Notify by time



