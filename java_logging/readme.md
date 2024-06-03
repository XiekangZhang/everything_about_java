# all related to java logging monitoring
## Apache Log4j
### Apache Log4j Architecture
- _Logger_: Loggers are created by calling _LogManager.getLogger_. The Logger itself performs no direct actions. 
It simply has a name and is associated with a _LoggerConfig_. It extends _AbstractLogger_ and implements the required
methods. 
- _LoggerConfig_ contains a set of Filters that must allow the LogEvent to pass before it will be passed to any _Appenders_.
- _Appender_ an output destination is called an _Appender_.
- _Layout_ defines the output format.